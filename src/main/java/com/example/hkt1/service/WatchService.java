package com.example.hkt1.service;

import com.example.hkt1.exception.WatchNotFoundException;
import com.example.hkt1.model.Watch;
import com.example.hkt1.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WatchService {

    private final WatchRepository watchRepository;

    public List<Watch> getAllWatches(){
        try{
            return watchRepository.findAll();
        } catch (Exception exception) {
            log.error("lỗi khi lấy danh sách wwatch", exception);
            throw exception;
        }
    }

    public Watch getWatchById(Long id) {
        return watchRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("không tìm thấy watch id: {}", id);
                    return new WatchNotFoundException(id);
                });
    }

    public Watch addWatch(Watch watch) {
        try {
            watch.setId(null);
            return watchRepository.save(watch);
        } catch (Exception exception) {
            log.error("lỗi khi thêm watch", exception);
            throw exception;
        }
    }

    public Watch updateWatch(Long id, Watch newWatch) {
        try {
            Watch existingWatch = getWatchById(id);

            existingWatch.setModelName(newWatch.getModelName());
            existingWatch.setBrand(newWatch.getBrand());
            existingWatch.setPrice(newWatch.getPrice());

            existingWatch.setMovementType(newWatch.getMovementType());
            existingWatch.setStatus(newWatch.getStatus());
            existingWatch.setIsDeleted(newWatch.getIsDeleted());

            return watchRepository.save(existingWatch);
        } catch (WatchNotFoundException exception) {
            throw exception;
        } catch (Exception exception) {
            log.error("loõi khi cập nhật watch: {}", id, exception);
            throw exception;
        }
    }

    public void deleteWatch(Long id) {
        try {
            Watch watch = getWatchById(id);
            watchRepository.delete(watch);
        } catch (WatchNotFoundException exception) {
            throw exception;
        } catch (Exception exception) {
            log.error("lỗi khi cố xoá watch với id: {}", id, exception);
            throw exception;
        }
    }

}
