package com.vssfullstack.agendadortarefas.controller;


import com.vssfullstack.agendadortarefas.business.TarefasService;
import com.vssfullstack.agendadortarefas.business.dto.TarefasDTO;
import com.vssfullstack.agendadortarefas.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;


    @PostMapping
    public ResponseEntity<TarefasDTO> salvarTarefas(@RequestBody TarefasDTO tarefasDTO,
                                                    @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.salvarTarefas(token, tarefasDTO));

    }



}
