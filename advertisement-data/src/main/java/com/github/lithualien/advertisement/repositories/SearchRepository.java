package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.*;
import com.github.lithualien.advertisement.vo.v1.advertisement.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchRepository {

    Page<ComputerAdvertisement> searchComputers(Pageable pageable, ComputerAdvertisementSearchVO searchVO);

    Page<PhoneAdvertisement> searchPhones(Pageable pageable, PhoneAdvertisementSearchVO searchVO);

    Page<ConsoleAdvertisement> searchConsole(Pageable pageable, ConsoleAdvertisementSearchVO searchVO);

    Page<ExternalDeviceAdvertisement> searchExternalDevice(Pageable pageable,
                                                           ExternalDeviceAdvertisementSearchVO searchVO);

    Page<MonitorAdvertisement> searchMonitor(Pageable pageable, MonitorAdvertisementSearchVO searchVO);
}
