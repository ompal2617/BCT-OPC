import { Component, OnInit, TemplateRef } from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms"; 
import {Router} from "@angular/router"; 
import { RoleService } from '../../../services/role.service';
import { Role } from '../../../model/role';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-show-role',
  templateUrl: './show-role.component.html',
  styleUrls: ['./show-role.component.css']
})
export class ShowRoleComponent implements OnInit {
 
  private  roleList:  Array<Role> = [];
  public modalRef: BsModalRef; 
  roleForm: FormGroup;
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder,private modalService: BsModalService,private router: Router,private roleService: RoleService) { }

  public openModal(roleTemplate: TemplateRef<any>) {
    this.formReset();
    this.modalRef = this.modalService.show(roleTemplate);
  }

  public editRole(roleTemplate: TemplateRef<any>,role: Role) {
    this.roleForm = this.formBuilder.group({
      id: [],
      role: ['', Validators.required] 
    }); 
    this.roleService.getRoleById(role.id)
    .subscribe( data => {
      this.roleForm.setValue(data);
    });
    this.modalRef = this.modalService.show(roleTemplate);
  }


  ngOnInit() {  
    this.getAllRoles();
    this.roleForm = this.formBuilder.group({
      id: [],
      role: ['', Validators.required] 
    }); 
  } 


  getAllRoles(){
    this.roleService.getRoles()
    .subscribe( (data:  Array<Role>) => {
      this.roleList = data;
      console.log(data);
    });
  }


  onSubmit() {
    this.roleService.createRole(this.roleForm.value)
      .subscribe( data => { 
      });
    this.modalRef.hide();
    this.getAllRoles();
    this.formReset();
    this.router.navigate(['/show-roles']);  
  }

  formReset(){
    this.roleForm = this.formBuilder.group({ 
      role: ''
    });
  }
 
}
