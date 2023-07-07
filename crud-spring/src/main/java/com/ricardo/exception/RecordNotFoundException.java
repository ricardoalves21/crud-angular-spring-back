package com.ricardo.exception;

public class RecordNotFoundException extends RuntimeException {

    private static long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Registro não encontrado com o id: " + id);
    }
}
