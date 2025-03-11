package com.novisign.assignment.repositories;

import com.novisign.assignment.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
