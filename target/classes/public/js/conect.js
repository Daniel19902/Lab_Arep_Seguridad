

var conect = (function (){
    const requestOptions = {
            method: 'GET',
            headers: {'Authorization': localStorage.getItem('Token') }
        };

    return{

        init: function(){
            console.log(localStorage.getItem('Data'))
            $('#fraces').html(localStorage.getItem('Data'))

        },

        conectarJava: function (user, password){
            fetch("https://localhost:4567/login",{
                method: 'POST',
                body: JSON.stringify({ userName:user, password:password}),
            })
                .then(response => response.json()).then( data => {
                console.log(data)
                if(data != "error de autenticacion"){
                    localStorage.setItem('Token', data);
                    fetch("https://localhost:4567/loby", requestOptions).then(response => response.json())
                    .then(data => {
                        console.log(data)
                        localStorage.setItem('Data', data);
                        location.href = 'loby.html'
                    })
                }

            })
        }


    }


})();