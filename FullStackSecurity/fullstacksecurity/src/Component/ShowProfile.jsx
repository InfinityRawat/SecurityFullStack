import { useEffect } from "react";
import { useState } from "react";


    function ShowProfile(){
            const [user,setUser] = useState({})
            const  getProfile = async ()=> {
                try{
                const  response =   await fetch(`http://localhost:8080/getStudentDetail?email=${localStorage.getItem("email")}`,{
                        headers:{
                            "Authorization":`Bearer ${localStorage.getItem("token")}`
                        }
                    });

                  const resp =  await response.json();

                  console.log(resp);
                console.log(localStorage.getItem("token"));
                
                    setUser({
                       name: resp.name,
                       email:  resp.email,
                        marks : resp.totalMarks
                    })
                  

                }
                catch(Error){
                        console.log(Error);
                        console.log(localStorage.getItem("token"));

                    console.log("Error occured !!");
                    
                }
            };

           useEffect(() => {
            getProfile();
            }, []);
          return (  <>
                <h1>User Profile:</h1>

                <p>Name of Student: {user.name}</p>
                <p>Email of Student: {user.email}</p>
                <p>Total Marks: {user.totalMarks}</p>

            </>
          )

    }

    export default ShowProfile;