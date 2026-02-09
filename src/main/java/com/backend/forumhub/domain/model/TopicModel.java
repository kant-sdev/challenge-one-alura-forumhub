package com.backend.forumhub.domain.model;

import com.backend.forumhub.domain.exception.BusinessException;
import com.backend.forumhub.domain.model.enums.StatusTopico;
import com.backend.forumhub.domain.model.enums.TipoForum;
import com.backend.forumhub.domain.model.enums.TipoTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserModel autor;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoForum tipoForum;
    private String curso;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTopico tipoTopico;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;
    @Column(nullable = false)
    private Boolean deleted = false;

    public TopicModel() {}

    public TopicModel(
            String titulo, String mensagem, UserModel autor,
            TipoForum tipoForum, String curso, TipoTopico tipoTopico
    ){
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.tipoForum = tipoForum;
        this.curso = curso;
        this.tipoTopico = tipoTopico;
        this.dataCriacao = LocalDateTime.now();
        this.status = tipoTopico == TipoTopico.DUVIDA
                ? StatusTopico.ABERTA
                : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public UserModel getAutor() {
        return autor;
    }

    public void setAutor(UserModel autor) {
        this.autor = autor;
    }

    public TipoForum getTipoForum() {
        return tipoForum;
    }

    public void setTipoForum(TipoForum tipoForum) {
        this.tipoForum = tipoForum;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public TipoTopico getTipoTopico() {
        return tipoTopico;
    }

    public void setTipoTopico(TipoTopico tipoTopico) {
        this.tipoTopico = tipoTopico;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public void setStatus(StatusTopico status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void softDelete() {
        if (this.deleted) {
            throw new BusinessException("Tópico já está excluído");
        }
        this.deleted = true;
    }
}


