import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from '@angular/router'; 
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import { routing } from  './app.routes';
import { AppComponent } from './app.component'; 
import { AddUserComponent } from './components/user/add-user/add-user.component';
import { ShowUserComponent } from './components/user/show-user/show-user.component';
import { AddRoleComponent } from './components/role/add-role/add-role.component';
import { ShowRoleComponent } from './components/role/show-role/show-role.component';
import { RoleService } from './services/role.service'
import { UserService } from './services/user.service'
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';



@NgModule({
  declarations: [
    AppComponent,       
    AddUserComponent,
    ShowUserComponent,
    AddRoleComponent,
    ShowRoleComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule, 
    routing,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule ,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),

  ],
  exports: [BsDropdownModule, TooltipModule, ModalModule],
  providers: [RoleService,UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
