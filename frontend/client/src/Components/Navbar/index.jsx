import React from 'react'
import { Box } from '@mui/system'
import { AppBar, Toolbar, Button, Menu, MenuItem, Typography, IconButton } from '@mui/material'
import Logo from "../../logo-transparent.png"
import { useState } from 'react'

const pages = ["Home", "Contest", "Problemset"]
const options = ["Sign in", "Sign up"]

const Index = () => {
  const [anchorElNav, setAnchorElNav] = useState(null)


  const handleOpenNav = (event) => {
    setAnchorElNav(event.currentTarget)
  }

  const handleCloseNav = (event) => {
    setAnchorElNav(null)
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar sx={{ background: "black" }}>
          <img src={Logo} width={"6%"} />
          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' }, marginLeft: "1%" }}>
            {pages.map((page) => (
              <Button
                key={page}
                sx={{ my: 2, color: 'white', display: 'block' }}
              >
                <Typography variant="body1">
                  {page}
                </Typography>
              </Button>
            ))}
          </Box>
          <Box sx={{ flexGrow: 0, display: { md: 'flex' } }}>
            {options.map((page) => (
              <Button
                key={page}
                sx={{ my: 2, color: "white", display: 'block' }}
              >
                {page}
              </Button>
            ))}
          </Box>
        </Toolbar>
      </AppBar>
    </Box>
  )
}
export default Index