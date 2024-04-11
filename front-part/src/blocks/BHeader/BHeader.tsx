import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { BLayoutHeader } from '../../layouts';
import { Link, useNavigate } from 'react-router-dom';

export const BHeader = () => {
  const navigate = useNavigate()
  const signOut = () => {
    localStorage.removeItem("auth");
    navigate('/signin')
  }

  const authCheck = localStorage.getItem("auth")

  return (
    <AppBar position="static" sx={{
      background: '#2c3441'
    }}>
        <Toolbar disableGutters sx={{
            mx: '15px'
          }}>
          <BLayoutHeader 
            leftContent={(
              <>
                <Button color="inherit" to='/' component={Link}>Главная</Button>
                <Button color="inherit" to='/courses' component={Link}>Курсы</Button>
                <Button color="inherit" to='/сompetitions' component={Link}>Соревнования</Button>
              </>
            )}
            rightContent={(
              <>
                {
                  authCheck ? (
                    <Button color="primary" variant='contained' onClick={signOut}>Выйти</Button>
                  ) : (
                    <>
                      <Button color="inherit" to='/registration' component={Link}>Регистрация</Button>
                      <Button color="primary" to='/signin' variant='contained' component={Link}>Войти</Button>
                    </>
                  )}
              </>
            )}
          />
        </Toolbar>
    </AppBar>
  );
}