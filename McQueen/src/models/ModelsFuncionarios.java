package models;

public class ModelsFuncionarios {
  private int id;
  private String nome;
  private String CPF;
  private String CEP;
  private String funcao;
  private int salario;
  private String status;

  public ModelsFuncionarios(){}

  public ModelsFuncionarios(String nome, String CPF, String CEP, String funcao, int Salario, String status){
    this.nome = nome;
    this.CPF = CPF;
    this.CEP = CEP;
    this.funcao = funcao;
    this.salario = Salario;
    this.status = status;
  }

  public ModelsFuncionarios(int id, String nome, String CPF, String CEP, String funcao, int salario, String status) {
    this.id = id;
    this.nome = nome;
    this.CPF = CPF;
    this.CEP = CEP;
    this.funcao = funcao;
    this.salario = salario;
    this.status = status;
  }

    public int getId() {
      return id;
  }

  public String getNome(){
    return nome;
  }

  public String getCPF(){
    return CPF;
  }

  public String getCEP(){
    return CEP;
  }

  public String getFuncao(){
    return funcao;
  }

  public int getSalario(){
    return salario;
  }

  public String getStatus(){
    return status;
  }

  public void setNome(String nome){
    this.nome = nome;
  }

  public void setCPF(String CPF){
    this.CPF = CPF;
  }

  public void setCEP(String CEP){
    this.CEP = CEP;
  }

  public void setFuncao(String funcao){
    this.funcao = funcao;
  }

  public void setSalario(int salario){
    this.salario = salario;
  }

  public void setStatus(String status ){
    this.status = status;
  }
}