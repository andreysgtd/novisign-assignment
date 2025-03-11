package com.novisign.assignment.controllers;

import com.novisign.assignment.exceptions.ImageNotFoundException;
import com.novisign.assignment.models.Image;
import com.novisign.assignment.models.ImageSearchRequest;
import com.novisign.assignment.models.ImageSearchResponse;
import com.novisign.assignment.models.Slideshow;
import com.novisign.assignment.services.ImageService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/addImage")
    public Image createImage(@RequestBody @Valid Image image) {
        return imageService.createImage(image);
    }

    @DeleteMapping("/deleteImage/{id}")
    public void deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImage(id);
        } catch (ImageNotFoundException ex1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex1.getMessage(), ex1);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "", e);
        }
    }

    @GetMapping("/images/search")
    public ImageSearchResponse searchImages(@RequestBody @Valid ImageSearchRequest imageSearchRequest) {
        Pair<List<Image>, Set<Slideshow>> result = imageService.getImagesAndSlideshowsByKeywordsAndDuration(
                imageSearchRequest.getKeywords(),
                imageSearchRequest.getMinDurationMillis(),
                imageSearchRequest.getMaxDurationMillis()
        );
        return new ImageSearchResponse(
                result.getLeft().toArray(new Image[0]),
                result.getRight().toArray(new Slideshow[0])
        );
    }
}
