# Basic GUI Rational Calcualtor
Uses basic MVC concepts 

## Models
Rational Class
> basic rational numbers class

## Views
OperatorView
> View for the operators (-, +, *, /, etc...)
> has no update options

## Controllers
Controller
> Implements basic calculator functions
> IE. clear, backspace, reset

Arithmetic flow
Note: ZERO */÷ by anything is ZERO

  if operatorInMemory do:
    if currentValueCanOperate do:
      result = eval valueInMemory (operator) currentValue
      clear both memories
      add result to currentValue
    end
  end

  updateView()

