package com.example.back_end.controller;

import com.example.back_end.dto.response.ColorResponse;
import com.example.back_end.service.admin.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/color")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<ColorResponse>> getAllColors() {
        List<ColorResponse> colors = colorService.getAll();
        return ResponseEntity.ok(colors);
    }
}
