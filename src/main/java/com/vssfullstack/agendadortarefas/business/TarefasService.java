package com.vssfullstack.agendadortarefas.business;


import com.vssfullstack.agendadortarefas.business.dto.TarefasDTO;
import com.vssfullstack.agendadortarefas.business.mapper.TarefasConverter;
import com.vssfullstack.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.vssfullstack.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.vssfullstack.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.vssfullstack.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO salvarTarefas(String token, TarefasDTO tarefasDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDTO.setEmailUsuario(email);
        TarefasEntity tarefasEntity = tarefasConverter.paraTarefaEntity(tarefasDTO);


        return tarefasConverter.paraTarefaDTO(
                tarefasRepository.save(tarefasEntity));

    }




}
