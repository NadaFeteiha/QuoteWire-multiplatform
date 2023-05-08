//
//  DetailScreen.swift
//  iosApp
//
//  Created by Nada Feteiha on 5/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation


import Foundation
import UIKit
import SwiftUI
import shared


struct DetailScreen: View {
    
    var quoteImage: QuoteImage
    
    @StateObject var viewModel = QuoteViewModel()
    
    var body: some View {
        VStack(alignment: .leading) {
            ZStack(alignment: .bottom)  {
                AsyncImage(url: URL(string: quoteImage.imageURL)){image in
                    image.resizable()
                        .aspectRatio(contentMode: .fill)
                        .edgesIgnoringSafeArea(.all)
                }placeholder: {
                    Color.gray
                }
                
                HStack(alignment: .center,spacing: 30){
                    
                    ShareLink(item: URL(string: quoteImage.imageURL)!) {
                        Image(systemName: "square.and.arrow.up")
                            .resizable()
                            .frame(width: 36, height: 36)
                    }
                    
                    Button(action: { Task { await viewModel.onClickFavorite()}}) {
                            Image(systemName: viewModel.quote.isSaved ? "heart.fill" : "heart")
                                .resizable()
                                .frame(width: 36, height: 36)
                        }
                }.padding(10)
                    .background(Color(red: 0.5, green: 0.5, blue:0.5))
                    .cornerRadius(10)
            }
        }.task {
            viewModel.setQuote(quoteImage: quoteImage )
            await  viewModel.initQuote(quoteId: quoteImage.id)
        }
    }
}
