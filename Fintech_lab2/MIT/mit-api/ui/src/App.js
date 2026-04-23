import * as React from 'react';
import AppBar from './component/AppBar';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import Login from './pages/Login';
import Customer from './pages/Customer';
import Admin from './pages/AdminPage';
import CustomerHistory from './pages/customerHistory';

const theme = createTheme({
  palette: {
    primary:{
      main:'#000a00'
    },
    secondary: {
      main: '#000a00',
    },
  },
});

function App() {
  return (
    <div style={{overflow:'auto'}}>
      <ThemeProvider theme={theme}>
        <Router>
          <Routes>
            <Route path="/" element={<AppBar componentName={"Login"} componentPage={<Login />} />} />
            <Route path="/customer" element={<AppBar componentName={"Customer"} componentPage={<Customer />} />} />
            <Route path="/admin" element={<AppBar componentName={"Admin"} componentPage={<Admin/>} />} />
            <Route path="/customer-history" element={<AppBar componentName={"History"} componentPage={<CustomerHistory/>} />} />
          </Routes>
        </Router>
      </ThemeProvider>
    </div>
  );
}

export default App;
