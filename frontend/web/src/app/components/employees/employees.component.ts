import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { FormControl } from '@angular/forms';


@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  constructor(private employeeService: DataService) { }

  firstName = new FormControl('');
  lastName = new FormControl('');
  pesel = new FormControl('');

  ngOnInit() {

  }

  saveEmployee() {
    console.log(this.firstName.value);
    this.employeeService.postEmployee(this.firstName.value, this.lastName.value, this.pesel.value)
      .subscribe(hero => hero);
  }

}
