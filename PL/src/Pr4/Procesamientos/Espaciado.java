package Pr4.Procesamientos;

import Pr4.AST.TinyASint.*;

public class Espaciado extends ProcesamientoPorDefecto {
    int dir;
    int nivel;

    public Espaciado() {
        dir = 0;
        nivel = 0;
    }

    public void procesa(Prog_con_decs prog) {
        prog.decs().procesa(this);
        prog.insts().procesa(this);
    }

    public void procesa(Prog_sin_decs prog) {
        prog.insts().procesa(this);
    }

    public void procesa(Decs_una decs) {
        decs.dec().procesa(this);
    }

    public void procesa(Decs_muchas decs) {
        decs.decs().procesa(this);
        decs.dec().procesa(this);
    }

    public void procesa(Dec_var dec) {
        dec.dir(dir);
        dec.nivel(nivel);
        dec.tipo().procesa(this); //TODO revisar asigna_espacio_tipo
        dir = dir + dec.tipo().tam();
    }

    public void procesa(Dec_tipo dec) {
        dec.tipo().procesa(this); //TODO revisar asigna_espacio_tipo
    }

    public void procesa(Dec_proc dec) {
        int ant_dir = dir;
        nivel = nivel + 1;
        dec.nivel(nivel);
        dir = 0;
        dec.params().procesa(this);
        dec.bloque().procesa(this);
        dec.tam_datos(dir);
        dir = ant_dir;
        nivel = nivel - 1;
    }

    public void procesa(Params_ninguno params) {}

    public void procesa(Params_uno_f params) {
        params.param().procesa(this);
    }

    public void procesa(Params_muchos_f params) {
        params.params().procesa(this);
        params.param().procesa(this);
    }

    public void procesa(Param_f_sin_amp param) {
        param.dir(dir);
        param.nivel(nivel);
        param.tipo().procesa(this); //TODO revisar asigna_espacio_tipo
        dir = dir + param.tipo().tam();
    }

    public void procesa(Param_f_con_amp param) {
        param.dir(dir);
        param.nivel(nivel);
        param.tipo().procesa(this); //TODO revisar asigna_espacio_tipo
        dir = dir + 1;
    }

    public void procesa(Bloque_lleno bloque) {
        bloque.prog().procesa(this);
    }

    public void procesa(Bloque_vacio bloque) {}

/*asigna_espacio_tipo(T) =
si indefinido(T.tam) entonces
asigna_espacio(T)*/ //TODO implementar
    public void procesa(Num_int tipo) {
        tipo.tam(1);
    }
    public void procesa(Num_real tipo) {
        tipo.tam(1);
    }
    public void procesa(True tipo) {
        tipo.tam(1);
    }
    public void procesa(False tipo) {
        tipo.tam(1);
    }
    public void procesa(Sstring tipo) {
        tipo.tam(1);
    }

    public void procesa(Tipo_id tipo) {
        tipo.vinculo().procesa(this);
        tipo.tam(tipo.vinculo().tam());
    }

    public void procesa(Tipo_array tipo) {
        tipo.tipo().procesa(this);
        tipo.tam(Integer.parseInt(tipo.valor().toString()) * tipo.tipo().tam());
    }

    public void procesa(Tipo_registro tipo) {
        tipo.campos().procesa(this);
        tipo.tam(tipo.campos().tam());
    }

    public void procesa(Campos_muchos campos) {
        campos.campos().procesa(this);
        campos.campo().procesa(this);
        campos.tam(campos.campos().tam() + campos.campo().tam());
    }

    public void procesa(Campos_uno campos) {
        campos.campo().procesa(this);
        campos.tam(campos.campo().tam());
    }

    public void procesa(Campo campo) {
        campo.tipo().procesa(this);
        campo.tam(campo.tipo().tam());
    }

    public void procesa(Tipo_puntero tipo) {
        tipo.tipo().procesa(this);
        tipo.tam(1);
    }

    public void procesa(Insts_ninguna insts) {}

    public void procesa(Insts_una insts) {
        insts.inst().procesa(this);
    }

    public void procesa(Insts_muchas insts) {
        insts.inst().procesa(this);
        insts.insts().procesa(this);
    }

    public void procesa(Inst_asig inst) {}

    public void procesa(Inst_ifthen inst) {
        inst.insts().procesa(this);
    }

    public void procesa(Inst_ifthenelse inst) {
        inst.bloque1().procesa(this);
        inst.bloque2().procesa(this);
    }

    public void procesa(Inst_while inst) {
        inst.insts().procesa(this);
    }

    public void procesa(Inst_lectura inst) {}
    public void procesa(Inst_escritura inst) {}
    public void procesa(Inst_new_line inst) {}
    public void procesa(Inst_reserv_mem inst) {}
    public void procesa(Inst_lib_mem inst) {}
    public void procesa(Inst_invoc_proc inst) {}

    public void procesa(Inst_comp inst) {
        int ant_dir = dir;
        nivel = nivel + 1;
        inst.nivel(nivel);
        dir = 0;
        inst.b().procesa(this);
        inst.tam(dir);
        dir = ant_dir;
        nivel = nivel - 1;
    }
}
