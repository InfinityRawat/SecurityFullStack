import { useState } from "react";
import ShowProfile from "./ShowProfile";


function Login(){

      const [email,setEmail] =  useState("");
      const [name,setName] =  useState("");
      const [password,setPassword] =  useState("");
      const [token,setToken] =  useState("");
      const [user,setUser] = useState({})

        const  getProfile = async (token)=> {
            try{

                console.log("Token is ");
                console.log(token);
                
            const  response =   await fetch(`http://localhost:8080/getStudentDetail?email=${email}`,{
                    headers:{
                        "Authorization":`Bearer ${token}`
                    },
                    method:"GET"
                });

              const resp =  await response.json();

            console.log(resp);
            // console.log(localStorage.getItem("token"));
            
                setUser({
                   name: resp.name,
                   email:  resp.email,
                    marks : resp.totalMarks
                })
              

            }
            catch(Error){
                    console.log(Error);
                    // console.log(localStorage.getItem("token"));

                console.log("Error occured !!");
                
            }
        };


    const loginHere= (event)=> {
            event.preventDefault()
            fetch("http://localhost:8080/login",{method:"POST",
                headers:{
                    "Content-Type":"application/json"
                },
                body: JSON.stringify({email,password})
            })
            .then((req)=>{
                console.log(req);
                if(!req.ok) throw new Error("Something went wrong!!")
               return req.json();
                        })
            .then((req)=>{
                console.log(req);
                console.log(`Inside Req token in ${req.token}`);
                
                    setToken(req.token);
                    setEmail(req.email);
                    // localStorage.setItem("token",token)
                    // localStorage.setItem("email",req.email)
                //    console.log(localStorage.getItem("token"));     
                    setName(req.studentName)
                    getProfile(req.token)
                   

            })
            .catch((req)=>{
                console.log(req);
                console.log("Problem in request/ server error");
            })

      } 
        return<>
                
                <h1>Login Form</h1>
            <form action="" method="get">

                <label htmlFor="username">Email</label>
                <input type="text" name="username" id="username" onChange={name=>setEmail(name.target.value)} />

                <label htmlFor="password">Password</label>
                <input type="text" name="password" id="password" onChange={pass=>setPassword(pass.target.value)}/>
                
                <input type="submit" value="submit" onClick={loginHere} />
            </form>

            {token && <p>Logged in token : {token}</p>}
            {token && <>
             <h1>User Profile:</h1>

            <p>Name of Student: {user.name}</p>
            <p>Email of Student: {user.email}</p>
            <p>Total Marks: {user.marks}</p>
            </>}
        </>
}

export default Login;