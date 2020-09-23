package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities", schema = "advertisement")
public class City extends BaseEntity {

    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.NO)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    private County county;

    public City(Long id, String city, County county) {
        super(id);
        this.city = city;
        this.county = county;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<UserPersonalInformation> userPersonalInformation = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<ComputerAdvertisement> computerAdvertisements = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<PhoneAdvertisement> phoneAdvertisements = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<ConsoleAdvertisement> consoleAdvertisements = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<ExternalDeviceAdvertisement> externalDeviceAdvertisements = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<MonitorAdvertisement> monitorAdvertisements = new HashSet<>();

}
