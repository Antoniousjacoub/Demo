compositeDisposable.add(newsRep.getNews(context, url, loginRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(loginView::showLoadingAnimation)
                .doFinally(loginView::hideLoadingAnimation)
                .subscribe(loginResponse -> {
                    loginView.onLoginResponseReceived(loginResponse);

                }, this::processError));
