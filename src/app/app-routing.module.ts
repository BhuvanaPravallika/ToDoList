import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { registerLocaleData } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AddComponent } from './add/add.component';
import { EditComponent } from './edit/edit.component';
import { ProfileComponent } from './profile/profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';

const routes: Routes = [
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"register",
    component:RegisterComponent
  },
  {
    path:"home",
    component:HomeComponent
  },
  {
    path:"add",
    component:AddComponent
  },

  {
    path:"edit",
    component:EditComponent
  },

  {
    path:"profile",
    component:ProfileComponent
  },

  {
    path:"edit-profile",
    component:EditProfileComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
