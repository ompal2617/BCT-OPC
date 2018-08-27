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
  isUpdated: boolean = false;
  successMessage: string;

  constructor(private formBuilder: FormBuilder,private modalService: BsModalService,private router: Router,private roleService: RoleService) { }

  public openModal(roleTemplate: TemplateRef<any>) {
    this.formReset();
    this.isUpdated = false;
    this.modalRef = this.modalService.show(roleTemplate);
  } 

  ngOnInit() {  
    this.successMessage = "Hello!";
    this.getAllRoles();
    this.roleForm = this.formBuilder
    .group({
            id: [],
            role: ['', Validators.required] 
          }); 
  }  

  onSubmit() {
    debugger
    if(this.isUpdated){
      this.updateRole(this.roleForm.value);
      this.successMessage = "Updated successfully!";
      this.modalRef.hide();
      this.getAllRoles();
      this.router.navigate(['/show-roles']);
    } else {
      this.createRole(this.roleForm.value);
      this.successMessage = "created successfully!";
      this.modalRef.hide();
      this.getAllRoles();
      this.router.navigate(['/show-roles']);
    } 
  }

  public createRole(role: Role) { 
    this.roleService.createRole(this.roleForm.value).subscribe( 
      data => {
        console.log("Created successfully!")         
      },(err) => {
        console.log("Error: ",err);
      }); 
  }

  public updateRole(role: Role) { 
    this.roleService.updateRole(this.roleForm.value).subscribe( 
      data => {
        console.log("Updated successfully!")         
      },(err) => {
        console.log("Error: ",err);
      }); 
  }

  public editRole(roleTemplate: TemplateRef<any>,role: Role) { 
    this.roleForm = this.formBuilder.group({
      id: [],
      role: ['', Validators.required] 
    }); 
    this.roleForm.setValue(role);
    this.isUpdated = true;
    this.modalRef = this.modalService.show(roleTemplate);
  }

  public deleteRole(id: number) { 
    debugger
    this.roleService.deleteRole(id).subscribe( 
      data => {
        console.log("Deleted successfully!")         
      },(err) => {
        console.log("Error: ",err);
      }); 
      this.successMessage = "Deleted successfully!";
      this.getAllRoles();
      this.router.navigate(['show-roles']);
  }

  formReset(){
    this.roleForm = this.formBuilder.group({ 
      role: ''
    });
  }
 
  getAllRoles(){
    this.roleService.getRoles()
    .subscribe( (data:  Array<Role>) => {
      this.roleList = data;
      console.log("Data:",data);
    }, (err) => {
      console.log("Error: ",err);
    });
  }
}
