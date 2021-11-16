package com.bugtracker.alpha.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.bugtracker.alpha.entities.Company;

public class CompanyDto implements Serializable {

  private long companyId;
  private String name;

  public CompanyDto() {

  }

  public CompanyDto(Company company) {
    this.companyId = company.getCompanyId();
    this.name = company.getName();
  }

  public long getCompanyId() {
    return this.companyId;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "{" +
      " companyId='" + getCompanyId() + "'" +
      ", name='" + getName() + "'" +
      "}";
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CompanyDto)) {
            return false;
        }
        CompanyDto companyDto = (CompanyDto) o;
        return companyId == companyDto.companyId && Objects.equals(name, companyDto.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyId, name);
  }

}
