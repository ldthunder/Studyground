package com.example.sg.data.mapper

import com.example.sg.data.network.DemonNetwork


fun List<DemonNetwork>.toDemon() = this.map { it }