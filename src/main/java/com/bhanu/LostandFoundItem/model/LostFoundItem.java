
package com.bhanu.LostandFoundItem.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lost_found_items")
@Getter
@Setter
@NoArgsConstructor
public class LostFoundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Size(max = 500)
    @Column(length = 500)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false, length = 10)
    private ItemType itemType;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String location;

    @NotBlank
    @Size(max = 100)
    @Column(name = "reported_by", nullable = false, length = 100)
    private String reportedBy;

    @Size(max = 150)
    @Column(name = "contact_info", length = 150)
    private String contactInfo;

    @NotNull
    @Column(name = "reported_date", nullable = false)
    private LocalDate reportedDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ItemStatus status;

    public enum ItemType {
        LOST,
        FOUND
    }

    public enum ItemStatus {
        OPEN,
        CLAIMED,
        RETURNED
    }
}
