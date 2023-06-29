import React from 'react'
import Home from './Home';
import '../cssFiles/logout.css'

function Logout() {
  localStorage.clear();
    
  return (
    <center>
    <div className='cntr'>Thank You! Visit me again</div>
    </center>
  )
}

export default Logout