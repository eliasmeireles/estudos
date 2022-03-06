package models

import (
	"gopkg.in/validator.v2"
	"gorm.io/gorm"
)

type Aluno struct {
	gorm.Model
	Nome string `json:"nome" validate:"min=3,max=40,regexp=^[a-zA-Z\\s]*$"`
	CPF  string `json:"cpf" validate:"regexp=^([0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}){1}$"`
	RG   string `json:"rg" validate:"min=11,max=12"`
}

func (a *Aluno) Valida() error {
	return validator.Validate(a)
}
