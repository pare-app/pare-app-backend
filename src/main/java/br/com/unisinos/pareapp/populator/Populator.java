package br.com.unisinos.pareapp.populator;

public interface Populator <S,T>{
    T populate(S source);
}
