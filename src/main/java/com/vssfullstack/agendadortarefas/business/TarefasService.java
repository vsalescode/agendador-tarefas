package com.vssfullstack.agendadortarefas.business;


import com.vssfullstack.agendadortarefas.business.dto.TarefasDTO;
import com.vssfullstack.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.vssfullstack.agendadortarefas.business.mapper.TarefasConverter;
import com.vssfullstack.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.vssfullstack.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.vssfullstack.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.vssfullstack.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.vssfullstack.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final TarefaUpdateConverter tarefaUpdateConverter;
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

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefasConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
    }


    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public TarefasDTO alteraStatusTarefa(StatusNotificacaoEnum statusNotificacaoEnum, String id) {
        TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tarefa não encontrada"));

        tarefasEntity.setStatusNotificacaoEnum(statusNotificacaoEnum);
        return tarefasConverter.paraTarefaDTO(tarefasRepository.save(tarefasEntity));


    }

    public TarefasDTO atualizarTarefas(TarefasDTO tarefasDTO, String id) {
        TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tarefa não encontrada"));
        tarefaUpdateConverter.atualizarTarefas(tarefasDTO, tarefasEntity);
        return tarefasConverter.paraTarefaDTO(tarefasRepository.save(tarefasEntity));


    }

    public void deletaTarefaPorId(String id) {
        if (!tarefasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id não encontrado");
        }

        tarefasRepository.deleteById(id);
    }


}
