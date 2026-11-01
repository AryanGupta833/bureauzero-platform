package com.bureauzero.core.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citizen_applications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CitizenApplication {
    @Id
    private String receiptId;
    @Column(nullable = false)
    private String fullName;
    private Integer age;
    private String gender;
    private String domicileState;
    private String socialCategory;
    private Long annualIncome;
    private String schemeName;
    private String citizenIdNumber;
    private String dobFormatted;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false, unique = true)
    private String cryptographicHash;
    private LocalDateTime submittedAt;
}
