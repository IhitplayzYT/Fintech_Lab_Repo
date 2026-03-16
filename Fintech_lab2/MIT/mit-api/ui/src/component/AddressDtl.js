import { Button, TextField } from '@mui/material';
import * as React from 'react';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
function AddressDtl(props) {
    const { onNextClick,onBackClick } = props;
    const [disableButton, setDisableButton] = React.useState(false);
    const [countryOfOrigin, setCountryOfOrigin] = React.useState();
    const [preferredLanguage, setPreferredLanguage] = React.useState();
    const [status, setStatus] = React.useState('Active');
    const [type, setType] = React.useState();
    const onCountryOfOriginvalueChange = async (value) => {
        setCountryOfOrigin(value.target.value)
    }
    const onPreferredLanguagevalueChange = async (value) => {
        setPreferredLanguage(value.target.value)
    }
    const onStatusvalueChange = async (value) => {
        setStatus(value.target.value)
    }
    const onTypevalueChange = async (value) => {
        setType(value.target.value)
    }
    const onNxtClick = async () => {
        let data = {
            countryOfOrigin:countryOfOrigin,
            preferredLanguage:preferredLanguage,
            status:status,
            type:type,
            index:2
        }
        onNextClick(data)
    }
    const onBckClick = async () => {
        let data = {
            index:0
        }
        onBackClick(data)
    }
    return (
        <div>
            <TextField label='Country Of Origin'
                value={countryOfOrigin}
                onChangeCapture={onCountryOfOriginvalueChange}
                onChange={onCountryOfOriginvalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <TextField label='Preferred Language'
                value={preferredLanguage}
                onChangeCapture={onPreferredLanguagevalueChange}
                onChange={onPreferredLanguagevalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <TextField label='Status'
                disabled
                value={status}
                onChangeCapture={onStatusvalueChange}
                onChange={onStatusvalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <FormControl style={{ width: '83%', marginRight: '20px' }}>
                <Select size='small'
                    value={type}
                    variant='outlined'
                    sx={{ width: '100%', mr: 0 }}
                    style={{ display: 'flex', borderColor: '#000a00', borderRadius: 8, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                    color='secondary'
                    onChange={onTypevalueChange}
                >
                    {
                        ['None', 'Platinum', 'Gold', 'Silver'].map((value) => (
                            <MenuItem value={value}>{value}</MenuItem>
                        ))
                    }
                </Select>
            </FormControl>
            <div style={{ display: 'flex' }}>
            <Button disabled={disableButton} disableElevation style={{marginLeft: '50px',marginTop:'30px', alignItems: 'end', borderColor:'#4bcd3e',color:'#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                    variant='outlined' onClick={onBckClick} sx={{ height: '100%',width:'13vw' }}>
                    Back
                </Button>
                <Button disabled={disableButton} disableElevation style={{marginLeft: '50px',marginTop:'30px', alignItems: 'end', color: disableButton ? '#b3b3b3' : '#000a00', borderRadius: 8, backgroundColor: disableButton ? '#f2f2f2' : '#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                    variant='contained' onClick={onNxtClick} sx={{ height: '100%',width:'13vw' }}>
                    Next
                </Button>
            </div>
        </div>
    );
}
export default AddressDtl;