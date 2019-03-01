function isEmailCorrect() {
    var email = document.getElementById("email");
    /*флаг i позволяет искать независимо от регистра
    судя по всему, эта регулярка не совсем полная*/
    if (email.value != "" && (/^[0-9a-z-\.]+\@[0-9a-z-]{2,}\.[a-z]{2,}$/i).test(email.value)) {
        return true
    }
    return false;
}

function isNameCorrect() {
    var firstName = document.getElementById("firstName")
    if (firstName.value != "" && (/^[A-ZА-я]+$/i).test(firstName.value)) {
    return true
    }
    return false;
}

function isPasswordCorrect() {

}





        var subButton = document.getElementById("submitButton");
        subButton.removeAttribute("disabled");
        subButton.style.color="green";
    }
    else {
        var subButton = document.getElementById("submitButton");
        subButton.setAttribute('disabled', 'disabled');
        subButton.style.color="red";
    }
}


