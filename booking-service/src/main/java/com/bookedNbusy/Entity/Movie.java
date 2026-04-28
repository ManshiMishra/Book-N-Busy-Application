package com.bookedNbusy.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Type;

import com.bookedNbusy.Enums.Genre;
import com.bookedNbusy.Enums.Language;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movietable")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true)
    private String movieName;

    @Column(name = "casts", columnDefinition = "TEXT[]")
    private List<String> casts;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Language movieLanguage;

    private LocalDate releaseDate;

    @Min(1)
    @Max(10)
    private Double rating;

    @PrePersist
    @PreUpdate
    public void normalizeRating() {
        if (rating != null) {
            rating = Math.max(1.0, Math.min(10.0, rating));
        }
    }

    private Double duration;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();

}
