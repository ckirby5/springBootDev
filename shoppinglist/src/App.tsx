import { useState } from "react";
import Container from "@mui/material/Container";
import {AppBar} from "@mui/material";
import {Toolbar} from "@mui/material";
import {Typography} from "@mui/material";
import {ListItem} from "@mui/material";
import {ListItemText} from "@mui/material";
import {List} from "@mui/material";
import './App.css'
import AddItem from "./AddItem.tsx";

export type Item = {
    product: string;
    amount: string;
}

function App() {
    const [items, setItems] = useState<Item[]>([]);
    const addItem = (item: Item) => {
        setItems([item, ...items]);
    }
  return (
      <Container>
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6">
                    Shopping List
                </Typography>
            </Toolbar>
        </AppBar>
          <AddItem addItem={addItem}/>
          <List>
              {
                  items.map((item, index) =>
                      <ListItem key={index} divider>
                          <ListItemText primary={item.product}
                                        secondary={item.amount}/>
                      </ListItem>
                  )
              }
          </List>
      </Container>
  )
}

export default App
