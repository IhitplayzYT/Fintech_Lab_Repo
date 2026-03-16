import { Button, TextField } from '@mui/material';
import * as React from 'react';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
function UpdateUserDtl(props) {
    const { rowData, onUpdateClick,onCancelClick } = props;
    const [firstName, setFirstName] = React.useState(rowData.customerName);
    const [lastName, setlastName] = React.useState('');
    const [dob, setDOB] = React.useState(rowData.customerDOB);
    const [middleName, setMiddleName] = React.useState('');
    const [gender, setGender] = React.useState(rowData.customerGender);
    const [countryOfOrigin, setCountryOfOrigin] = React.useState(rowData.customerCountryOrigin);
    const [preferredLanguage, setPreferredLanguage] = React.useState(rowData.customerPreferredLanguage);
    const [status, setStatus] = React.useState('Active');
    const [type, setType] = React.useState(rowData.customerType);
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
    const onFirstNamevalueChange = async (value) => {
        setFirstName(value.target.value)
    }
    const onLastNamevalueChange = async (value) => {
        setlastName(value.target.value)
    }
    const onDOBvalueChange = async (value) => {
        setDOB(value.target.value)
    }
    const onMiddleNamevalueChange = async (value) => {
        setMiddleName(value.target.value)
    }
    const onUpdate =async () => {
        let requestBody = {
            customerType: type,
            customerName: [
                {
                    "type": "FirstName",
                    "value": firstName
                }
            ],
            customerDOB: dob,
            customerStatus: "Active",
            customerGender: gender,
            customerPreferredLanguage: preferredLanguage
        }
        onUpdateClick(requestBody);
    }
    return (
        <div>
            <TextField label='First Name'
                value={firstName}
                onChangeCapture={onFirstNamevalueChange}
                onChange={onFirstNamevalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <TextField label='Middle Name'
                value={middleName}
                onChangeCapture={onMiddleNamevalueChange}
                onChange={onMiddleNamevalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <TextField label='Last Name'
                value={lastName}
                onChangeCapture={onLastNamevalueChange}
                onChange={onLastNamevalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <TextField label=''
                helperText=''
                value={dob}
                onChangeCapture={onDOBvalueChange}
                onChange={onDOBvalueChange}
                variant='outlined'
                size='small'
                type='date'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
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
            <FormControl style={{ width: '83%', marginRight: '20px' }}>
                <Select size='small'
                    value={gender}
                    variant='outlined'
                    sx={{ width: '100%', mr: 0 }}
                    style={{ display: 'flex', borderColor: '#000a00', borderRadius: 8, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                    color='secondary'
                    onChange={(value) => setGender(value.target.value)}
                >
                    {
                        ['Male', 'Female', 'Other'].map((value) => (
                            <MenuItem value={value}>{value}</MenuItem>
                        ))
                    }
                </Select>
            </FormControl>
            <div style={{ display: 'flex', justifyContent: 'center' }}>
                <Button disableElevation style={{ marginTop: '30px', alignItems: 'end', borderColor: '#4bcd3e', color: '#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                    variant='outlined' onClick={onCancelClick} sx={{ height: '100%', width: '13vw' }}>
                    Cancel
                </Button>
                <Button disableElevation style={{ marginLeft: '50px', marginTop: '30px', alignItems: 'end', color: '#000a00', borderRadius: 8, backgroundColor: '#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                    variant='contained' onClick={onUpdate} sx={{ height: '100%', width: '13vw' }}>
                    Confirm
                </Button>
            </div>
        </div>
    )
}

export default UpdateUserDtl;