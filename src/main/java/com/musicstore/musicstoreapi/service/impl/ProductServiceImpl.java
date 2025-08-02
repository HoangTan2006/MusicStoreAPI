package com.musicstore.musicstoreapi.service.impl;

import com.musicstore.musicstoreapi.dto.request.productDTO.ProductRequest;
import com.musicstore.musicstoreapi.dto.request.productDTO.ProductUpdateRequest;
import com.musicstore.musicstoreapi.dto.response.productDTO.ListProductResponse;
import com.musicstore.musicstoreapi.dto.response.productDTO.ProductDetailResponse;
import com.musicstore.musicstoreapi.entity.Category;
import com.musicstore.musicstoreapi.entity.Product;
import com.musicstore.musicstoreapi.mapper.ProductMapper;
import com.musicstore.musicstoreapi.repository.CartItemRepository;
import com.musicstore.musicstoreapi.repository.CategoryRepository;
import com.musicstore.musicstoreapi.repository.ProductRepository;
import com.musicstore.musicstoreapi.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public void CreateProduct(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Product product = Product.builder()
                .name(productRequest.getName())
                .imageUrl(productRequest.getImageUrl())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .soldQuantity(productRequest.getSoldQuantity())
                .category(category)
                .isDeleted(false)
                .build();

        productRepository.save(product);
    }

    @Override
    public ProductDetailResponse getProduct(Integer id) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productMapper.toProductDetailDTO(product);
    }

    @Override
    public ListProductResponse getProducts(Integer page, Integer size, String sort) {
        Pageable pageable;
        if (sort != null) {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+,(asc|desc)$");
            Matcher matcher = pattern.matcher(sort);
            if (!matcher.matches()) throw new InvalidParameterException("Invalid sort parameter format. Expected format: field,asc|desc");

            String[] sortParam = sort.split(",");
            Sort.Direction direction = (sortParam[1].equals("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC;

            pageable = PageRequest.of(page, size, Sort.by(direction, sortParam[0]));
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Product> products = productRepository.findAllByIsDeletedFalse(pageable);
        return ListProductResponse.builder()
                .products(
                        products.getContent()
                        .stream()
                        .map(productMapper::toProductDTO)
                        .toList())
                .currentPage(page)
                .totalPage(products.getTotalPages())
                .build();
    }

    @Override
    public void updateProduct(Integer id, ProductUpdateRequest productUpdate) {
        if (productUpdate.getPrice() == null && productUpdate.getStockQuantity() == null) return;
        Product product = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (productUpdate.getPrice() != null) product.setPrice(productUpdate.getPrice());
        if (productUpdate.getStockQuantity() != null) product.setStockQuantity(productUpdate.getStockQuantity());

        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        product.setIsDeleted(true);
        productRepository.save(product);

        cartItemRepository.deleteAllByProduct_Id(id);
    }
}
