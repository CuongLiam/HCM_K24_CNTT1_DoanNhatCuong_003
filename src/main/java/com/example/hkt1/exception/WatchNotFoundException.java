package com.example.hkt1.exception;

public class WatchNotFoundException extends RuntimeException{
    public WatchNotFoundException(Long id){
        super("Không tìm thấy watch có id: "+id);
    }
}
