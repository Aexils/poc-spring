package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductImageDTO;
import com.aexils.pocspring.entity.ProductImage;
import com.aexils.pocspring.mapper.ProductImageMapper;
import com.aexils.pocspring.repository.ProductImageRepository;
import com.aexils.pocspring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final ProductImageMapper productImageMapper;

    public List<ProductImage> getImagesByProductId(String productId) {
        return productImageRepository.findByProductId(productId);
    }

    public ProductImageDTO addImage(String productId, MultipartFile file, boolean isMain) throws IOException {
        // 1. Sauvegarde physique de l'image
        String folder = "uploads/products/";
        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(folder, filename);

        Files.createDirectories(filePath.getParent());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String imageUrl = "/uploads/products/" + filename;

        // 2. Création de l'entité
        ProductImage image = new ProductImage();
        image.setProduct(productRepository.getReferenceById(productId));
        image.setUrl(imageUrl);
        image.setMain(isMain);

        // 3. Si image principale, reset les autres
        if (isMain) {
            productImageRepository.unsetMainForProduct(productId);
        }

        // 4. Save + retour
        productImageRepository.save(image);
        return productImageMapper.toDto(image);
    }

    public void deleteImage(String imageId) {
        Optional<ProductImage> image = productImageRepository.findById(imageId);
        image.ifPresent(productImageRepository::delete);
    }

    @Transactional
    public void setMainImage(String productId, String imageId) {
        productImageRepository.unsetMainForProduct(productId);

        ProductImage mainImage = productImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        mainImage.setMain(true);
        productImageRepository.save(mainImage);
    }

    public String save(String filename, byte[] content) {
        try {
            // Créer le dossier s'il n'existe pas
            String uploadDir = "uploads/products";
            Path uploadPath = Paths.get(uploadDir);
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Nettoyer le nom du fichier (éviter ../ etc.)
            String safeFilename = UUID.randomUUID() + "_" + Paths.get(filename).getFileName().toString();

            // Créer le chemin complet
            Path filePath = uploadPath.resolve(safeFilename);

            // Sauvegarder le fichier
            Files.write(filePath, content);

            // Retourner l’URL publique (à adapter selon ta stratégie d’expo)
            return "/uploads/" + safeFilename;

        } catch (IOException e) {
            throw new RuntimeException("Impossible de sauvegarder l'image", e);
        }
    }
}
