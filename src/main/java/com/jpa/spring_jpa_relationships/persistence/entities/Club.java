package com.jpa.spring_jpa_relationships.persistence.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // CascadeType.PERSIST
    // When you insert a club also will be created a coach
    // But when you delete a club the coach still exists
    @OneToOne(targetEntity = Coach.class, cascade = CascadeType.PERSIST)
    // Change the name from coach_id to id_coach
    // @JoinColumn(name = "id_coach")
    private Coach coach;

    // FetchType.LAZY: If you loaded a club, the players
    // only will be loaded when you requested (getPlayers)
    // Fetchtype.EAGER: If you request a club, the players
    // will also be obtained
    @OneToMany(targetEntity = Player.class, fetch = FetchType.LAZY, mappedBy = "club")
    private List<Player> players;
    // Whe you have OneToMany, the entity that is "One"
    // will be passed like foreign key to the entity "Many"

    @ManyToOne(targetEntity = FootballAssociation.class)
    private FootballAssociation footballAssociation;

    @ManyToMany(targetEntity = FootballCompetition.class, fetch = FetchType.LAZY)
    @JoinTable(name = "club_competitions", joinColumns = @JoinColumn(name = "club"), inverseJoinColumns = @JoinColumn(name = "competition"))
    private List<FootballCompetition> competitions;

}
