

var conect = (function (){

    let aes = "ec2-35-175-248-225.compute-1.amazonaws.com"
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
            fetch("https://ec2-35-175-248-225.compute-1.amazonaws.com:45647/login",{
                method: 'POST',
                body: JSON.stringify({ userName:user, password:password}),
            })
                .then(response => response.json()).then( data => {
                console.log(data)
                if(data != "error de autenticacion"){
                    localStorage.setItem('Token', data);
                    fetch("https://ec2-35-175-248-225.compute-1.amazonaws.com:45647/loby", requestOptions).then(response => response.json())
                    .then(data => {
                        console.log(data)
                        localStorage.setItem('Data', data);
                        location.href = 'loby.html'
                    })
                }

            })
        },

        sumar : function(uno, dos){
            console.log(uno, dos)
            fetch("https://ec2-35-175-248-225.compute-1.amazonaws.com:45647/sumar/"+uno+"/"+dos, requestOptions).then(response => response.json())
            .then(data => {

            $('#respuesta').html(data)

            })


        }


    }


})();