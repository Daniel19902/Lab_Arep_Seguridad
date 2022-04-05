

var conect = (function (){


    return{

        conectarJava: function (user, password){
            fetch("https://localhost:4567/login",{
                method: 'POST',
                body: JSON.stringify({ userName:user, password:password}),
            })
                .then(response => response.json()).then( data => {
                console.log(data)

            })
        }


    }


})();