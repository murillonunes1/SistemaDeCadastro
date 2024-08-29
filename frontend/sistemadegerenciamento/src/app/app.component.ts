import { CommonModule } from '@angular/common';
import { Block } from '@angular/compiler';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { ifError } from 'assert';
import exp from 'constants';
import { blob } from 'stream/consumers';
import { __classPrivateFieldSet } from 'tslib';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  @ViewChild('myModal') model : ElementRef | undefined
  @ViewChild('myModal2') myModal2 : ElementRef | undefined
  studentObj: Student = new Student();
  studentList: Student[] = []
  title = 'sistemaDeGerenciamentoDeUsuario';

  ngOnInit() : void {
    
    var newUrl = 'http://localhost:8080';
    fetch(newUrl,{method:"GET"}).then(response => {
      console.log("teste"+ response)
        if(!response.ok)
            throw new Error('Erro ao tentar buscar a lista!');
        return response.json();
    }).then(data => {
            this.studentList = data;
    }).catch(error => {
            console.error('Erro ao a converter a lista', error);
    });
  }

  openDetails(item: Student): void {
    const modal2 = document.getElementById("myModal2");
    this.studentObj = { ...item };
    if (modal2) {
      modal2.style.display = 'block';
    }
  }

  onEdit(item:Student){
    this.studentObj = {...item}
    this.openModelUpdate();
  }

  openModelUpdate(){
    const model = document.getElementById("myModal")
    if (model != null) {
      model.style.display = 'block';
    }
  }

  onDelete(item: Student){
    this.studentObj = item;
    const isDelet = confirm("Você realmente deseja deletar?")
    if (isDelet) {
      var novaURL = `http://localhost:8080/${this.studentObj.id}`;
    fetch(novaURL, {
        method: "DELETE",
        redirect: "follow"
    }).then(response => {
        if(!response.ok){
            throw new Error("Error")
        }
    }).then(data => {
        console.log(data);
        window.location.reload();
    }).catch((error)=> console.log("error", error));
    }
  }

  openModel(){
    const model = document.getElementById("myModal")
    if (model != null) {
      model.style.display = 'block';
    }
    this.studentObj = new Student(); // Resetar studentObj para um novo objeto vazio
    this.openModel();
  }

  closeModel() {
    const model = document.getElementById("myModal")
    if (model != null) {
      model.style.display = 'none';
    }
  }

  closeModel2() {
    const model = document.getElementById("myModal2")
    if (model != null) {
      model.style.display = 'none';
    }
  }

  saveStudent() {

    let dados = {
      endereco:this.studentObj.endereco,
      departamento: {
        departamento:this.studentObj.departamento.departamento
      },
      cidade:this.studentObj.cidade,
      email:this.studentObj.email,
      telefone:this.studentObj.telefone,
      nome:this.studentObj.nome,
      estado:this.studentObj.estado,
      cep:this.studentObj.cep
    }
    
    fetch('http://localhost:8080', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body:JSON.stringify(dados)
  }).then(d =>{
    this.closeModel()
    this.clearForm()
    window.location.reload()
    this.ngOnInit()
  })

 
  }

  updateStud(){
    
    let dados = {
      endereco:this.studentObj.endereco,
      departamento: {
        departamento:this.studentObj.departamento.departamento
      },
      cidade:this.studentObj.cidade,
      email:this.studentObj.email,
      telefone:this.studentObj.telefone,
      nome:this.studentObj.nome,
      estado:this.studentObj.estado,
      cep:this.studentObj.cep
    }
  fetch(`http://localhost:8080/${this.studentObj.id}`, {
      method: 'PUT',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify(dados)
  }).then(d =>{
    this.closeModel()
    window.location.reload()
  })

  }

  clearForm(): void {
    this.studentObj = new Student();
  }

}

export class Student {
  id:number;
  nome : string;
  telefone: string;
  email: string;
  cidade: string;
  estado: string;
  cep: string;
  endereco: string;
  departamento: {
    departamento: string; 
};

  constructor(){
    this.id = 0;
    this.endereco ='';
    this.departamento = {departamento: 'TI'}; 
    this.cidade='';
    this.email='';
    this.telefone ='';
    this.nome= '';
    this.estado = ''; 
    this.cep='';
  }
}
