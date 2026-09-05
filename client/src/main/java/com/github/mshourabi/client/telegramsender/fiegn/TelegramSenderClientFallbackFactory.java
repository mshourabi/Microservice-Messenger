//package com.github.mshourabi.client.telegramsender.fiegn;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.openfeign.FallbackFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TelegramSenderClientFallbackFactory
//        implements FallbackFactory<TelegramSenderClient> {
//
//    private static final Logger log =
//            LoggerFactory.getLogger(TelegramSenderClientFallbackFactory.class);
//
//    public TelegramSenderClientFallbackFactory() {
//        System.out.println("========== TelegramSenderClientFallbackFactory CREATED ==========");
//    }
//
//    @Override
//    public TelegramSenderClient create(Throwable cause) {
//
//        System.out.println("========== TelegramSenderClientFallbackFactory CREATE ==========");
//        cause.printStackTrace();
//
//        return request -> {
//
//            System.out.println("========== TelegramSenderClient FALLBACK EXECUTED ==========");
//
//            return ResponseEntity
//                    .status(HttpStatus.SERVICE_UNAVAILABLE)
//                    .build();
//        };
//    }
//}