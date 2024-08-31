import { useState } from 'react'

import './App.css'
import Login from './Component/Login'
import ShowProfile from './Component/ShowProfile'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>

      <Login />
      {/* {(localStorage.getItem("token")!=null) && <ShowProfile  />} */}

    </>
  )
}

export default App
