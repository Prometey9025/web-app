import { Box } from '@mui/material';
import { BHeader } from '../../blocks';
import { Outlet } from "react-router-dom";

export const BLayoutApp = () => {
  return (
    <Box sx={{display: 'flex', flexDirection: 'column', height: '100vh'}}>
      <BHeader />
      <Box sx={{height: '100%'}}>
        <Outlet/>
      </Box>
    </Box>
  );
};
