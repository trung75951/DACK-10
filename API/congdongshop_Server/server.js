const fs = require('fs')
const bodyParser = require('body-parser')
const jsonServer = require('json-server')
const jwt = require('jsonwebtoken')

const server = jsonServer.create()

const router = jsonServer.router('./db.json')

const db = JSON.parse(fs.readFileSync('./db.json', 'UTF-8'))

const middlewares = jsonServer.defaults();
const port = process.env.PORT || 3000;

server.use(middlewares);


server.use(jsonServer.defaults());
server.use(bodyParser.urlencoded({extended: true}))
server.use(bodyParser.json())

const SECRET_KEY = '123456789'
const expiresIn = '1h'

function createToken(payload) {
    return jwt.sign(
        payload, 
        SECRET_KEY, 
        {expiresIn})
}

function verifyToken(token) {
    return jwt.verify(
        token, 
        SECRET_KEY,  
        (err, decode) => decode !== undefined ?  decode : err)
}

function isAuthenticated({email, password}){
    return db.users.findIndex(user => user.email === email && user.password === password) !== -1
}

//Đăng ký
server.post('/dang-ky-tai-khoan', (req, res) => {
  const {username, email, password} = req.body;

  exist_user = db.users.findIndex(x => x.email === email) //kiểm tra Đăng ký - email tồn tại
  if(exist_user !== -1) {
    return res.status(401).json({
      status: 401,
      message: "Email đã được sử dụng!",
    })
  }

  const new_user = {
    'id': db.users.length+1,
    username,
    email,
    password
  }

  db.users.push(new_user);
  fs.writeFileSync('./db.json', JSON.stringify(db), () => {
    if (err) return console.log(err);
    console.log('writing to ' + fileName);
  })
  res.status(201).json({
    status: 201,
    message: "Đăng ký thành công",
    data: new_user
  })
})

//Đăng nhập
server.post('/dangnhap', (req, res) => {
  // const {email, password} = req.body
  const email = req.body.email
  const password = req.body.password

  if (isAuthenticated({email, password}) === false) { // kiểm tra Đăng nhập - email sai
    const status = 401
    const message = 'Email hoặc mật khẩu không chính xác'
    res.status(status).json({status, message})
    return
  }
  const access_token = createToken({email, password})
  res.status(200).json({
    status: 200,
    message: "Chúc mừng bạn đã đăng nhập thành công",
    data: {
      access_token
    }
  })
})


server.use('/auth',(req, res, next) => {
  if (req.headers.authorization == undefined || req.headers.authorization.split(' ')[0] !== 'Bearer') {
    const status = 401
    const message = 'Không tìm thấy token'
    res.status(status).json({status, message})
    return
  }
  try {
    let verifyTokenResult;
     verifyTokenResult = verifyToken(req.headers.authorization.split(' ')[1]);

     if (verifyTokenResult instanceof Error) {
       const status = 401
       const message = 'Lỗi: Token không hợp lệ'
       res.status(status).json({status, message})
       return
     }
     next()
  } catch (err) {
    const status = 401
    const message = 'Token đã hết hạn'
    res.status(status).json({status, message})
  }
})


//Danh sách người dùng
server.get('/auth/users', (req, res) => {
  res.status(200).json({
    status: 200,
    data: {
      "users" : db.users
    }
  })
})

//thay đổi mật khẩu và username
server.patch('/auth/doithongtin/:id', (req, res) => {
  const users_id = req.params.id
  const usersname = req.body.usersname
  const password = req.body.password

  const exist_users = db.users.findIndex(users => users.id == users_id)
  if(exist_users !== -1) {
    db.users[exist_users].usersname = usersname
    db.users[exist_users].password = password

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    res.status(200).json({
      status: 200,
      message: "Thay đổi thông tin thành công",
      data: {
        'user': db.users[exist_users]
      }
    })
  } else {
    res.status(401).json({
      status: 401,
      message: "Id users không tồn tại",
    })
  }

})

//Tìm người dùng bằng email
server.get('/auth/users/:email', ((req, res)=> {
  //let userdb = JSON.parse(fs.readFileSync('./database.json', 'UTF-8'));
  let email = req.params.email;
 
let result = db.users.filter(user =>  user.email == email)

  const status = 200
    
    res.status(status).json({status, result})
    return
}))

//Xóa tài khoản bằng email
server.delete('/auth/users/:email', (req, res) => {
  const email = req.params.email

  const exist_email = db.users.findIndex(user =>  user.email == email)
  if(exist_email !== -1) {
    db.users.splice(exist_email, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Thành công",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Email không tồn tại",
    })
  }

})
// End đăng nhập đăng ký

//liên hệ
server.post('/lien-he', (req, res) => {
  const {name, email, phone, content} = req.body;

  const new_lienhe = {
    'id': db.lienhe.length+1,
    name,
    email,
    phone,
    content
  }

  db.lienhe.push(new_lienhe);
  fs.writeFileSync('./db.json', JSON.stringify(db), () => {
    if (err) return console.log(err);
    console.log('writing to ' + fileName);
  })
  res.status(201).json({
    status: 201,
    message: "Thông tin liên hệ đã được gửi",
    data: new_lienhe
  })
})

//Xem danh sách liên hệ
server.get('/auth/lien-he', (req, res) => {
  res.status(200).json({
    status: 200,
    data: {
      "lienhe" : db.lienhe
    }
  })
})

//Thay đổi nội dung liên hệ
server.patch('/auth/lien-he/:id', (req, res) => {
  const lienhe_id = req.params.id
  const content = req.body.content

  const exist_lienhe = db.lienhe.findIndex(lienhe => lienhe.id == lienhe_id)
  if(exist_lienhe !== -1) {
    db.lienhe[exist_lienhe].content = content

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    res.status(200).json({
      status: 200,
      message: "Thay đổi nội dung thành công",
      data: {
        'lienhe': db.lienhe[exist_lienhe]
      }
    })
  } else {
    res.status(401).json({
      status: 401,
      message: "Liên hệ không tồn tại",
    })
  }

})

//Xem liên hệ bằng email
server.get('/auth/lien-he/:email', ((req, res)=> {
  //let userdb = JSON.parse(fs.readFileSync('./database.json', 'UTF-8'));
  let email = req.params.email;
 
let result = db.lienhe.filter(lienhe =>  lienhe.email == email)

  const status = 200
    
    res.status(status).json({status, result})
    return
}))

//Xóa liên hệ bằng id
server.delete('/auth/lien-he/:id', (req, res) => {
  const lienhe_id = req.params.id

  const exist_lienhe = db.lienhe.findIndex(lienhe => lienhe.id == lienhe_id)
  if(exist_lienhe !== -1) {
    db.lienhe.splice(exist_lienhe, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Xóa liên hệ thành công",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Id không tồn tại",
    })
  }

})
//END liên hệ

//Đặt một mô hình
server.post('/auth/don-hang', (req, res) => {
  const {mohinhId, username} = req.body
  const exist_mohinh_id = db.mohinh.findIndex(mohinh => mohinh.id === mohinhId)

  if(exist_mohinh_id === -1) {
    return res.status(401).json({
      status: 401,
      message: "Không tìm thấy mô hình",
    })
  }

  const order_mohinh = db.mohinh[exist_mohinh_id]
  if(order_mohinh.available) {
    const new_order = {
      'id': db.orders.length+1,
      mohinhId,
      username,
      "soluong": 1,
      "timestamp": new Date().getTime()
    }
  
    db.orders.push(new_order);
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })
    return res.status(200).json({
      status: 200,
      message: "Đặt thành công",
      data: new_order
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Mô hình đã hết hàng",
    })
  }
})

//Xem đơn hàng bằng id
server.get('/auth/don-hang/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
      res.status(200).json({
            status: 200,
            data: {
              'orders': db.orders[exist_order]
            }
          })
    } else {
      return res.status(401).json({
        status: 401,
        message: "Đơn hàng không tồn tại",
      })
    }
  })

//Cập nhật số lượng đơn hàng
server.patch('/auth/don-hang/:id', (req, res) => {
  const order_id = req.params.id
  const soluong = req.body.soluong

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
    db.orders[exist_order].soluong = soluong

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    res.status(200).json({
      status: 200,
      message: "Cập nhật thành công",
      data: {
        'orders': db.orders[exist_order]
      }
    })
  } else {
    res.status(401).json({
      status: 401,
      message: "Không tìm thấy đơn hàng",
    })
  }

})

//Xem tất cả đơn hàng
server.get('/auth/don-hang', (req, res) => {
  res.status(200).json({
    status: 200,
    message: "Tất cả đơn hàng",
    data: {
      "orders" : db.orders
    }
  })
})

//Xóa đơn hàng bằng id
server.delete('/auth/don-hang/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
    db.orders.splice(exist_order, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Thành công",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Không tìm thấy đơn hàng",
    })
  }

})


// thêm sản phẩm mô hình
server.post('/new-mohinh', (req, res) => {
  const {name, price, modelstatus, company, type, size, from, Commit, available} = req.body;

  const new_mohinh = {
    'id': db.mohinh.length+1,
    name,
    price,
    modelstatus,
    company,
    type,
    size,
    from,
    Commit,
    available
  }

  db.mohinh.push(new_mohinh);
  fs.writeFileSync('./db.json', JSON.stringify(db), () => {
    if (err) return console.log(err);
    console.log('writing to ' + fileName);
  })
  res.status(201).json({
    status: 201,
    message: "Thêm thành công",
    data: new_mohinh
  })
})

//xem tất cả mô hình
server.get('/mohinh', (req, res) => {
  res.status(200).json({
    status: 200,
    "mohinh" : db.mohinh
  })
})

//xem mô hình bằng id
server.get('/auth/mohinh/:id', (req, res) => {
  const mohinh_id = req.params.id

  const exist_mohinh = db.mohinh.findIndex(mohinh => mohinh.id == mohinh_id)
  if(exist_mohinh !== -1) {
      res.status(200).json({
            status: 200,
            data: {
              'mohinh': db.mohinh[exist_mohinh]
            }
          })
    } else {
      return res.status(401).json({
        status: 401,
        message: "Mô hình không tồn tại",
      })
    }
  })

//Update sản phẩm
server.patch('/auth/mohinh/:id', (req, res) => {
  const mohinh_id = req.params.id
  const name = req.body.name
  const price = req.body.price
  const modelstatus = req.body.modelstatus
  const company = req.body.company
  const type = req.body.type
  const size = req.body.size
  const from = req.body.from
  const Commit = req.body.Commit
  const available = req.body.available

  const exist_mohinh = db.mohinh.findIndex(mohinh => mohinh.id == mohinh_id)
  if(exist_mohinh !== -1) {
    db.mohinh[exist_mohinh].name = name
    db.mohinh[exist_mohinh].price = price
    db.mohinh[exist_mohinh].modelstatus = modelstatus
    db.mohinh[exist_mohinh].company = company
    db.mohinh[exist_mohinh].type = type
    db.mohinh[exist_mohinh].size = size
    db.mohinh[exist_mohinh].from = from
    db.mohinh[exist_mohinh].Commit = Commit
    db.mohinh[exist_mohinh].available = available


    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    res.status(200).json({
      status: 200,
      message: "Thay đổi sản phẩm thành công",
      data: {
        'mohinh': db.mohinh[exist_mohinh]
      }
    })
  } else {
    res.status(401).json({
      status: 401,
      message: "Mô hình không tồn tại",
    })
  }

})

//Xóa mô hình bằng id
server.delete('/auth/mohinh/:id', (req, res) => {
  const mohinh_id = req.params.id

  const exist_mohinh = db.mohinh.findIndex(mohinh => mohinh.id == mohinh_id)
  if(exist_mohinh !== -1) {
    db.mohinh.splice(exist_mohinh, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Xóa mô hình thành công",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Id không tồn tại",
    })
  }

})

server.use(router)

server.listen(3000, () => {
  console.log('Run Auth API Server')
})