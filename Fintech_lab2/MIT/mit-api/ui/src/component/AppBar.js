import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import CssBaseline from '@mui/material/CssBaseline';
import Typography from '@mui/material/Typography';
import Logo from '../assets/FIS.png';
import MenuItem from '@mui/material/MenuItem';
import { useNavigate } from "react-router-dom";
import Menu from '@mui/material/Menu';

const drawerWidth = 240;


const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));


export default function MiniDrawer(props) {
        const navigate = useNavigate();
    const { componentPage,componentName } = props;

    return (
        <div>
            <CssBaseline />
            <AppBar elevation={2} position="static" style={{ backgroundColor: 'white', color: 'black', height:'10%' }}>
                <Toolbar>
                    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', textAlign: 'center' }}>
                        <img src={Logo} alt='Logo' width={80} height={35} style={{ marginBottom: '10px', marginLeft: '0' }} />
                        <hr style={{ height: 20, border: 'none', borderRight: '1px solid #000a00', marginLeft: '3px', marginRight: '3px' }} />
                        <Typography color='#000a00' noWrap component="div" style={{ fontSize: 26, fontWeight: 'normal' }}>
                            Banking Platform
                        </Typography>
                    </div>
                        {componentName !== "Login"?<MenuItem style={{marginLeft:'45%'}} key={1} onClick={() => {
                            navigate("/customer-history");
                        }}>
                            <Typography color='#000a00' noWrap component="div" style={{ fontWeight: 'normal' }}>
                                Customer History
                            </Typography>
                        </MenuItem>:<div></div>}
                        {componentName !== "Login"?<MenuItem key={1} onClick={() => {
                            navigate("/admin");
                        }}>
                            <Typography color='#000a00' noWrap component="div" style={{ fontWeight: 'normal' }}>
                                Search Customer
                            </Typography>
                        </MenuItem>:<div></div>}
                </Toolbar>
            </AppBar>
            <Box style={{ width: `calc(100% - 0px)`, backgroundColor: '#f1f1f1' }}>
                {componentPage}
            </Box>
        </div>
    );
}