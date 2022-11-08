import React, { useState, useEffect } from 'react'
import Api from "../../Api"
import ReactMarkdown from "react-markdown"
import { Typography, Card, CardHeader, Avatar, CardContent, CardActionArea } from "@mui/material"

const Blog = () => {

    const [blogs, setBlogs] = useState([])

    useEffect(() => {
        Api.get("/user/lastest-blogs", { params: { limit: 5 } })
            .then((res) => {
                const blogsList = []
                for (var i = 0; i < res["data"].length; i++) {
                    blogsList.push(res["data"][i]);
                }
                setBlogs(blogsList)
            })
            .catch((err) => { })
    }, [])

    return (
        <>
            {blogs.map((blog) => (
                <Card
                    variant="outlined"
                    id={blog.uid}
                    sx={{ maxWidth: "60%", marginTop: "1%", marginLeft: "2%", marginRight: "2%", maxHeight: "10%", boxShadow: "2px 2px 2px 2px #c4c4c4" }}
                >
                    <CardActionArea
                        onClick={() => { window.location.href = "/blogs/" + blog.uid }}
                    >
                        <CardHeader
                            avatar={
                                <Avatar sx={{ background: `#${Math.floor(Math.random() * 16777215).toString(16)}` }}>{blog.createdBy[0]}</Avatar>
                            }
                            title={blog.createdBy}
                            subheader={blog.createdAt}
                        />
                        <CardContent>
                            <Typography variant="h4">{blog.title}</Typography>
                            <ReactMarkdown>
                                {blog.content.substring(0, 300)}
                            </ReactMarkdown>
                        </CardContent>
                    </CardActionArea>
                </Card>
            ))}
        </>
    )
}

export default Blog 