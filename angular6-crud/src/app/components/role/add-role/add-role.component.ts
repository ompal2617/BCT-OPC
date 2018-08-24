import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, FormBuilder, Validators} from "@angular/forms"; 
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import {first} from "rxjs/operators";
import {Router} from "@angular/router";
import { RoleService } from '../../../services/role.service';

@Component({
  selector: 'app-add-role',
  templateUrl: './add-role.component.html',
  styleUrls: ['./add-role.component.css']
})
export class AddRoleComponent implements OnInit {

  roleForm: FormGroup;
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder,private router: Router,private roleService : RoleService) { }


  ngOnInit() {
    this.roleForm = this.formBuilder.group({
      id: [],
      role: ['', Validators.required] 
    });
  }

  onSubmit() {
    this.roleService.createRole(this.roleForm.value)
      .subscribe( data => {
        this.router.navigate(['show-roles']);
      });
      this.formReset();
  }

  formReset(){
    this.roleForm = this.formBuilder.group({ 
      role: ''
    });
  }

}
