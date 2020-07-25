package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.DozerConverter;
import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.repositories.ComputerAdvertisementRepository;
import com.github.lithualien.advertisement.vo.v1.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.vo.v1.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComputerAdvertisementServiceImpl extends AbstractAdvertisementService<ComputerAdvertisement> implements ComputerAdvertisementService {

    private final ComputerAdvertisementRepository computerAdvertisementRepository;

    public ComputerAdvertisementServiceImpl(ComputerAdvertisementRepository computerAdvertisementRepository) {
        this.computerAdvertisementRepository = computerAdvertisementRepository;
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> all(Pageable pageable) {
        Page<ComputerAdvertisement> computerAdvertisements = super.all(pageable, computerAdvertisementRepository);
        return super
                .all(pageable, computerAdvertisementRepository)
                .map(this::convert);
    }

    @Override
    public ComputerAdvertisementVO save(ComputerAdvertisementVO computerAdvertisementVO) {
        return null;
    }

    @Override
    public ComputerAdvertisementVO update(ComputerAdvertisementVO computerAdvertisementVO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private ComputerAdvertisementWithImageVO convert(ComputerAdvertisement computerAdvertisement) {
        return DozerConverter.parseObject(computerAdvertisement, ComputerAdvertisementWithImageVO.class);
    }
}
