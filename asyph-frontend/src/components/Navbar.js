import { AppBar, MenuItem, Toolbar, Typography } from "@mui/material";
import { Container } from "@mui/material";
import Logo from "./logo.gif";
import { useState } from "react";
import { Box } from "@mui/system";
import { Menu, Avatar, Tooltip, IconButton, Divider } from "@mui/material";
import ListItemIcon from "@mui/material/ListItemIcon";
import Settings from "@mui/icons-material/Settings";
import Logout from "@mui/icons-material/Logout";
import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
import NotificationsIcon from "@mui/icons-material/Notifications";

const Navbar = () => {
  const [anchorEl, setAnchorEl] = useState();
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = (event) => {
    setAnchorEl(null);
  };

  return (
    <AppBar position="static">
      <Container maxWidth="x1" className="toolbar">
        <Toolbar disableGutters className="toolbar">
          <img src={Logo} width="70" className="logo" />
          <Typography
            variant="h5"
            noWrap
            sx={{
              marginLeft: 2.5,
            }}
          >
            Asyph-OJ
          </Typography>
          
          <Box
            id="account-settings"
            sx={{ display: "flex", alignItems: "center", textAlign: "center" }}
            className="profile-nav--bar"
          >
            <IconButton
            className="toolbar--notifications"
            sx={{
              backgroundColor: "#b8b4ca",
              width: "20%",
              height: "10%",
              display: "flex",
              alignItem: "center"
            }}
          >
            <NotificationsIcon style={{ color: "black" }} />
          </IconButton>
            <Tooltip title="Account settings">
              <IconButton
                onClick={handleClick}
                size="small"
                sx={{ ml: 2 }}
                aria-controls={open ? "account-menu" : undefined}
                aria-haspopup="true"
                aria-expanded={open ? "true" : undefined}
              >
                <Avatar sx={{ width: 60, height: 60 }}>S</Avatar>
                <Typography
                  variant="body2"
                  style={{
                    color: "white",
                    marginLeft: "10px",
                    marginBotton: "10px",
                  }}
                >
                  Srikanth Iyengar
                </Typography>
              </IconButton>
            </Tooltip>
          </Box>
          <Menu
            anchorEl={anchorEl}
            id="account-menu"
            open={open}
            onClose={handleClose}
            onClick={handleClose}
            PaperProps={{
              elevation: 0,
              sx: {
                overflow: "visible",
                filter: "drop-shadow(0px 2px 8px rgba(0,0,0,0.32))",
                mt: 1.5,
                "& .MuiAvatar-root": {
                  width: 32,
                  height: 32,
                  ml: -0.5,
                  mr: 1,
                },
                "&:before": {
                  content: '""',
                  display: "block",
                  position: "absolute",
                  top: 0,
                  right: 14,
                  width: 10,
                  height: 10,
                  bgcolor: "background.paper",
                  transform: "translateY(-50%) rotate(45deg)",
                  zIndex: 0,
                },
              },
            }}
            transformOrigin={{ horizontal: "right", vertical: "top" }}
            anchorOrigin={{ horizontal: "right", vertical: "bottom" }}
          >
            <MenuItem>
              <Avatar /> Profile
            </MenuItem>
            <Divider />
            <MenuItem>
              <ListItemIcon>
                <Settings fontSize="medium" />
              </ListItemIcon>
              Settings
            </MenuItem>
            <MenuItem>
              <ListItemIcon>
                <Logout fontSize="medium" />
              </ListItemIcon>
              Logout
            </MenuItem>
          </Menu>
        </Toolbar>
      </Container>
    </AppBar>
  );
};

export default Navbar;
